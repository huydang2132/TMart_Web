import axios from "axios";

export const chatbot = async (content) => {
    try {
        const res = await axios.post('http://localhost:6969/api/v1/chatbot', { prompt: content });
        return res.data;
    } catch (error) {
        throw new Error(error);
    }
}