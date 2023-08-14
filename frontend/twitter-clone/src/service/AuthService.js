import axios from "axios";

export default class AuthService {

    login(body) {
        return axios.post("/auth/login", body) // see how apis are called from frontend using axios, this is how we integrate
    }

    signup(body) {
        return axios.post("/auth/signup", body)
    }
}