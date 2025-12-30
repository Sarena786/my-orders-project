import axios from "axios";

axios.interceptors.request.use(config => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

const API = axios.create({
    baseURL: "http://localhost:8080/api/products"
});

// JWT is already attached by interceptor globally

export function getProducts() {
    return API.get("");
}

export function addProduct(form) {
    const formData = new FormData();

    formData.append("name", form.name);
    formData.append("pricePerKg", form.pricePerKg);
    formData.append("quantity", form.quantity);
    formData.append("note", form.note || "");

    return API.post("", formData);
}

export function updateProduct(id, data) {
    return API.put(`/${id}`, data);
}

export function deleteProduct(id) {
    return API.delete(`/${id}`);
}
