import baseService from "./baseService";
import axios from '../axios';

class userService extends baseService {
    endpoint = "/users";

    async getByToken(token) {
        const res = await axios.get(`${this.endpoint}/token/${token}`);
        return res;
    }

    async uploadFile(id, file) {
        const res = await axios.post(`${this.endpoint}/upload-image/${id}`, file, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        return res;
    }

    async editProfile(id, data) {
        const res = await axios.put(`${this.endpoint}/edit-profile/${id}`, data);
        return res;
    }

    async changePassword(id, data) {
        const res = await axios.put(`${this.endpoint}/change-password/${id}`, data);
        return res;
    }
}

export default new userService();