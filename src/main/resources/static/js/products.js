/**
 * Product Management Logic fetching from API and syncing localStorage
 */
const STORAGE_KEY = 'tgdd_products';

const DEFAULT_PRODUCTS = [
    {
        name: "iphone 17",
        price: 15000000,
        oldPrice: 0,
        discount: 0,
        image: `/images/samsung-galaxy-a16-gray-thumb-600x600.jpg`,
        link: "#",
        promotion: false
    },
    {
        name: "Case anh chính",
        price: 150000,
        oldPrice: 0,
        discount: 0,
        image: `/images/phu-kien-24x24.png`,
        link: "#",
        promotion: false
    },
    {
        name: "ip17",
        price: 150000,
        oldPrice: 0,
        discount: 0,
        image: `/images/oppo-a60-blue-thumb-1-600x600.jpg`,
        link: "#",
        promotion: false
    },
    {
        name: "ip16",
        price: 15000,
        oldPrice: 0,
        discount: 0,
        image: `/images/vivo-y39-xanh-thumb-600x600.jpg`,
        link: "#",
        promotion: false
    }
];

// Helper to get products
async function getProducts() {
    let prods = [];

    // Unconditional migration of local data to DB
    const localData = localStorage.getItem(STORAGE_KEY);
    if (localData) {
        try {
            const parsed = JSON.parse(localData);
            await fetch('/api/products/sync', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(parsed)
            });
            localStorage.removeItem(STORAGE_KEY);
        } catch (e) {
            console.error("Migration of products failed:", e);
        }
    }

    try {
        const response = await fetch('/api/products');
        if (response.ok) {
            prods = await response.json();
        }
    } catch (e) {
        console.error("Error fetching products:", e);
    }

    // Map categoryName from DB back to category for frontend compatibility
    prods = prods.map(p => {
        return {
            ...p,
            category: p.categoryName || null
        };
    });

    if (!prods || prods.length === 0) {
        try {
            await fetch('/api/products/sync', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(DEFAULT_PRODUCTS)
            });
            const res = await fetch('/api/products');
            if (res.ok) prods = await res.json();
            prods = prods.map(p => ({ ...p, category: p.categoryName || null }));
        } catch (e) {
            prods = DEFAULT_PRODUCTS;
        }
    }

    return prods;
}

// Add a new product
async function addProduct(product) {
    try {
        await fetch('/api/products', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(product)
        });
    } catch (e) {
        console.error("Add failed", e);
    }
}

// Update an existing product
async function updateProduct(id, product) {
    try {
        await fetch(`/api/products/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(product)
        });
    } catch (e) {
        console.error("Update failed", e);
    }
}

// Delete a product (optional for now, but good for admin)
async function deleteProduct(id) {
    try {
        await fetch(`/api/products/${id}`, {
            method: 'DELETE'
        });
    } catch (e) {
        console.error("Delete failed", e);
    }
}
