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

// Hàm nhóm các danh mục theo parent để hiển thị trên select/dropdown (vd trong admin.html)
async function getGroupedCategories() {
    const cats = await getCategories();
    const grouped = {};
    cats.forEach(c => {
        const p = c.parent || 'Danh mục chính';
        if (!grouped[p]) {
            grouped[p] = [];
        }
        grouped[p].push(c);
    });
    return grouped;
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
