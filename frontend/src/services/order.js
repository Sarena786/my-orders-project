import API from "./api";

export function getOrders() {
    return API.get("/orders");
}

export function createOrder(data) {
    return API.post("/orders", data);
}

export function deleteOrder(id) {
    return API.delete(`/orders/${id}`);
}
