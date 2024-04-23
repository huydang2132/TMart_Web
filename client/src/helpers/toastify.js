import { toast } from "vue3-toastify";
import "vue3-toastify/dist/index.css";

export const toastify = (title, type = 'info', position = 'top-center',) => {
    toast(title, {
        "theme": "colored",
        "type": type,
        "position": position,
        "dangerouslyHTMLString": true
    })
}