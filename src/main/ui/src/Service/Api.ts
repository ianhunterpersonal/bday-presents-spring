
import axios from 'axios';

import { UserType } from '../Components/UserContext';
export interface LoginCallback {
    handleLogin(user: UserType | null): void;
};

export function login(email: string, password: string, callback: LoginCallback) {

    console.log("===> Api.login()");
    axios.post('/v1/sessions/login', {
        email: email,
        password: password
    }).then(function (resp) {
        console.log("===> Api.login(). Server returns " + resp.data);
        callback.handleLogin(resp.data);
    }).catch(function (error) {
        console.log("Error " + error)
    }
    );

}

