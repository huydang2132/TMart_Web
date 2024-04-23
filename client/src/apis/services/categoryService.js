import axios from '../axios';

class categoryService {
    gettAll = async () => {
        try {
            const res = await axios.get('/categories');
            return res.data;
        } catch (error) {
            console.error(error);
        }
    }

    getById = async (id) => {
        try {
            const res = await axios.get(`/categories/${id}`);
            return res.data;
        } catch (error) {
            console.error(error);
        }
    }
}

export default new categoryService();