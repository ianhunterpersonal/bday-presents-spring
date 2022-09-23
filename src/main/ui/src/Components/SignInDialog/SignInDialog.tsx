import * as React from 'react';
import { useContext } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

import { UserContext, UserType } from '../UserContext';
import {login, LoginCallback} from '../../Service/Api';

export default function SignInDialog() {

    const userContext = useContext(UserContext);

    const notLoggedIn: boolean = (userContext?.user?.loginToken == null);

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        const loginCallback: LoginCallback = {
            handleLogin: (user: UserType) => {
                userContext.setUser(user);
            }
        };
        login('_Test_Person_1@email.com', 'xxx', loginCallback);
        setOpen(!open);
    };

    return (
        <div>
            <span>Dialog will appear if user not logged in not LoggedIn = {userContext.user?.name}</span>
            <Dialog open={notLoggedIn} onClose={handleClose}>
                <DialogTitle>Subscribe</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        To subscribe to this website, please enter your email address here. We
                        will send updates occasionally.
                    </DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="name"
                        label="Email Address"
                        type="email"
                        fullWidth
                        variant="standard"
                    />
                </DialogContent>
                <DialogActions>
                    <div id="login-button"><Button onClick={handleClose}>Log in</Button></div>
                </DialogActions>
            </Dialog>
        </div>
    );
}