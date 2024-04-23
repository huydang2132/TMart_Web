import axios from '../axios';

class productService {
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

    async getById(id) {
        try {
            const res = await axios.get(`/products/${id}`);
            return res.data;
        } catch (error) {
            console.error(error);
        }
    }
}

export default new productService();