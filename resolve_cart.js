const fs = require('fs');
const path = 'c:/Users/tsmdo/Downloads/Ngogiaphuong-2280602523/src/main/resources/templates/cart/cart.html';
let content = fs.readFileSync(path, 'utf8');

// 1. Update pay1 label to MoMo
content = content.replace(/Qua ví MWG Paylater/, 'Qua ví MoMo');
content = content.replace(/\(Lãi suất 0%, trả góp dễ dàng\)/, '(Thanh toán nhanh chóng, an toàn)');

// 2. Resolve submitOrder conflict
const conflictRegex = /async function submitOrder\(\) \{[\s\S]*?<<<<<<< HEAD[\s\S]*?=======[\s\S]*?>>>>>>> origin\/Dung/;
const resolvedSubmitOrder = `async function submitOrder() {
            const cart = getCart();
            if (cart.length === 0) {
                alert('Giỏ hàng trống!');
                return;
            }

            // Recalculate totals for order submission
            let subtotal = cart.reduce((acc, item) => acc + item.price * item.quantity, 0);
            let totalQty = cart.reduce((acc, item) => acc + item.quantity, 0);
            let shippingFee = (subtotal >= 1000000 && totalQty >= 2) ? 0 : 30000;
            const finalTotal = subtotal + shippingFee;

            let momoSelected = document.getElementById('pay1').checked;

            if (momoSelected) {
                let form = document.createElement("form");
                form.method = "POST";
                form.action = "/momo/create";

                let amountInput = document.createElement("input");
                amountInput.type = "hidden";
                amountInput.name = "amount";
                amountInput.value = finalTotal;
                form.appendChild(amountInput);

                let infoInput = document.createElement("input");
                infoInput.type = "hidden";
                infoInput.name = "orderInfo";
                infoInput.value = "Thanh toán đơn hàng Giỏ hàng TGDĐ";
                form.appendChild(infoInput);

                let nameInput = document.createElement("input");
                nameInput.type = "hidden";
                nameInput.name = "customerName";
                nameInput.value = "Anh Ngô Gia Phương";
                form.appendChild(nameInput);

                // Send repeated IDs for quantities to handle flash sale stock properly
                cart.forEach(item => {
                    for(let i=0; i<item.quantity; i++) {
                        let productInput = document.createElement("input");
                        productInput.type = "hidden";
                        productInput.name = "productIds";
                        productInput.value = item.id;
                        form.appendChild(productInput);
                    }
                });

                document.body.appendChild(form);
                form.submit();
                localStorage.removeItem(CART_KEY);
                return;
            }

            // Normal API order (for other payment methods)
            const orderData = {
                customerName: "Ngô Gia Phương",
                customerEmail: "phuong@example.com",
                customerPhone: "0123456789",
                shippingAddress: "TP. Hồ Chí Minh",
                products: cart.flatMap(item => Array(item.quantity).fill({ id: item.id })),
                status: 'PENDING'
            };

            try {
                const response = await fetch('/api/orders', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(orderData)
                });

                if (response.ok) {
                    alert('Đặt hàng thành công! Cảm ơn bạn đã mua sắm.');
                    localStorage.removeItem(CART_KEY);
                    window.location.href = '/';
                } else {
                    const err = await response.json();
                    alert('Lỗi khi đặt hàng: ' + (err.message || 'Vui lòng thử lại sau.'));
                }
            } catch (error) {
                console.error('Order error:', error);
                alert('Không thể kết nối đến máy chủ. Vui lòng kiểm tra lại.');
            }
        }`;

content = content.replace(conflictRegex, resolvedSubmitOrder);

fs.writeFileSync(path, content, 'utf8');
console.log('Successfully resolved cart.html conflicts');
