import API from "./api";

export function login(username, password) {
    return API.post("/users/login", { username, password });
}

export function register(username, password) {
    return API.post("/users/register", { username, password });
}
