import baseService from "./baseService";
import axios from '../axios';

class userService extends baseService {
    endpoint = "/users";

    async getByToken(token) {
        try {
            const res = await axios.get(`${this.endpoint}/token/${token}`);
            return res.data;
        } catch (error) {
            console.error(error);
        }

    }
}

export default new userService();