/**
 * Product Management Logic using LocalStorage
 */

const STORAGE_KEY = 'tgdd_products';

// Helper to get products
function getProducts() {
    const products = localStorage.getItem(STORAGE_KEY);
    return products ? JSON.parse(products) : [];
}

// Helper to save products
function saveProducts(products) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(products));
}

// Add a new product
function addProduct(product) {
    const products = getProducts();
    // Assign a simple ID based on timestamp
    product.id = Date.now();
    products.push(product);
    saveProducts(products);
    return product;
}

// Delete a product (optional for now, but good for admin)
function deleteProduct(id) {
    let products = getProducts();
    products = products.filter(p => p.id != id);
    saveProducts(products);
}

// Default/Initial data (optional: if we want to pre-populate)


// Initialize on load
initDefaultData();
