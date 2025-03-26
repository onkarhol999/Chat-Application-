import axios from "axios"; // ✅ Correct
export const baseURL= "http://localhost:8080";
export const  httpClient = axios.create({
    baseURL: baseURL,
})
