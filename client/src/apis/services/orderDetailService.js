import axios from "../axios";

class orderDetailService {
    endpoint = "/order-details";

    async getAllByOrderId(id) {
        const res = await axios.get(`${this.endpoint}/order/${id}`);
        return res;
    }
}

export default new orderDetailService();