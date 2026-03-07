async function getCategories() {
    let cats = [];
    try {
        const response = await fetch('/api/categories');
        if (response.ok) {
            cats = await response.json();
        }
    } catch (e) {
        console.error("Error fetching categories:", e);
    }
    return cats;
}

async function addCategory(category) {
    try {
        await fetch('/api/categories', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(category)
        });
    } catch (e) {
        console.error(e);
    }
}

async function updateCategory(id, updatedCategory) {
    try {
        await fetch(`/api/categories/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updatedCategory)
        });
    } catch (e) {
        console.error(e);
    }
}

async function deleteCategory(id) {
    try {
        await fetch(`/api/categories/${id}`, {
            method: 'DELETE'
        });
    } catch (e) {
        console.error(e);
    }
}
