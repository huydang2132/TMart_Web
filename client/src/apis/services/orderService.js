import axios from '../axios';
import baseService from './baseService';

class orderService extends baseService {

    endpoint = "/orders";

    async calculateTotalPrice(data, discount) {
        const res = await axios.post(`${this.endpoint}/calculate`, data, { params: { discount: discount } });
        return res;
    }

    async getAllByUser(id) {
        const res = await axios.get(`${this.endpoint}/user/${id}`);
        return res;
    }
}

export default new orderService();