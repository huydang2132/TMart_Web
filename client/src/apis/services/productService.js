import axios from '../axios';
import baseService from './baseService';

class productService extends baseService {
    endpoint = "/products";

    async getAll(page, perPage) {
        try {
            const res = await axios.get('/products', { params: { page: page, perPage: perPage } });
            return res.data;
        } catch (error) {
            console.error(error);
        }
    }

    async getAllByCategory(id, page, perPage) {
        try {
            const res = await axios.get(`/products/category/${id}`, { params: { page, perPage } });
            return res.data;
        } catch (error) {
            console.error(error);
        }
    }

    async getAllDeleted(page, perPage) {
        const res = await axios.get('/products/deleted', { params: { page: page, perPage: perPage } });
        return res;
    }

    async uploadImage(id, data) {
        const res = await axios.post(`${this.endpoint}/upload-image/${id}`, data, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        return res;
    }
}

export default new productService();