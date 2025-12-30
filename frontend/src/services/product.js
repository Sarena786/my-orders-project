import API from "./api";

export function getProducts() {
    return API.get("/products");
}

export function addProduct(form) {
    const formData = new FormData();
    formData.append("name", form.name);
    formData.append("pricePerKg", form.pricePerKg);
    formData.append("quantity", form.quantity);
    formData.append("note", form.note || "");
    return API.post("/products", formData);
}

export function updateProduct(id, data) {
    return API.put(`/products/${id}`, data);
}

export function deleteProduct(id) {
    return API.delete(`/products/${id}`);
}