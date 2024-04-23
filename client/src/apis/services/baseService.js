import axios from '../axios';

class baseService {
    constructor() { }

    endpoint = "";

    async getAll() {
        const res = await axios.get(this.endpoint);
        return res;
    }

    async getById(id) {
        const res = await axios.get(`${this.endpoint}/${id}`);
        return res;

    }

    async insert(data) {
        const res = await axios.post(this.endpoint, data);
        return res;
    }

    async update(id, data) {
        const res = await axios.put(`${this.endpoint}/${id}`, data);
        return res;
    }

    async delete(id) {
        const res = await axios.delete(`${this.endpoint}/${id}`);
        return res;
    }
}

export default baseService;