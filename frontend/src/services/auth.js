import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api/users",
    headers: {
        "Content-Type": "application/json"
    }
});

API.interceptors.request.use(config => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});


export function login(username, password) {
    return API.post("/login", { username, password });
}

export function register(username, password) {
    return API.post("/register", { username, password });
}
