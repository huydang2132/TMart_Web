import { GoogleGenerativeAI } from "@google/generative-ai";

const MODEL_NAME = "tunedModels/tmartprompt-attj80qw9r2s";
const API_KEY = process.env.API_KEY;

const genAI = new GoogleGenerativeAI(API_KEY);

export const chatbot = async (req, res) => {
    try {
        const prompt = req.body.prompt || 'Xin chào';
        const model = genAI.getGenerativeModel({ model: MODEL_NAME });
        const generationConfig = {
            temperature: 0.9,
            topK: 1,
            topP: 1,
            maxOutputTokens: 2024,
        };
        const parts = [
            { text: prompt },
        ]
        const result = await model.generateContent({
            contents: [{ role: "user", parts }],
            generationConfig,
        });
        const response = await result.response;
        const text = response.text();
        res.status(200).json(text);
        // console.log(response);
    } catch (error) {
        console.log(error);
        res.status(500).json({ error: error });
    }
}