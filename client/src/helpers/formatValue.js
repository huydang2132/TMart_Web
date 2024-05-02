const formatValue = {
    formatMoney(value) {
        return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
    },

    formatDateTime(date) {
        let dateTime = new Date(date);
        let dateString =
            ("0" + dateTime.getDate()).slice(-2) + "/" +
            ("0" + (dateTime.getMonth() + 1)).slice(-2) + "/" +
            dateTime.getFullYear() + " " +
            ("0" + dateTime.getHours()).slice(-2) + ":" +
            ("0" + dateTime.getMinutes()).slice(-2) + ":" +
            ("0" + dateTime.getSeconds()).slice(-2);
        return dateString;
    },
    formatDate(date) {
        let dateTime = new Date(date);
        let dateString =
            ("0" + dateTime.getDate()).slice(-2) + "/" +
            ("0" + (dateTime.getMonth() + 1)).slice(-2) + "/" +
            dateTime.getFullYear();
        return dateString;
    }
}

export default formatValue;