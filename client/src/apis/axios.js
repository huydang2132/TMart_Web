import axios from "axios";

const instance = axios.create({
    baseURL: 'https://tmartweb-production.up.railway.app/api/v1'
});

instance.interceptors.request.use((config) => {
    const user = JSON.parse(localStorage.getItem('user'));
    const token = user?.token;
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
});

export default instance;