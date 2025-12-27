import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api/products",
    headers: {
        "Content-Type": "application/json"
    }
});

// JWT is already attached by interceptor globally

export function getProducts() {
    return API.get("");
}

export function addProduct(data) {
    return API.post("", data);
}

export function updateProduct(id, data) {
    return API.put(`/${id}`, data);
}

export function deleteProduct(id) {
    return API.delete(`/${id}`);
}
