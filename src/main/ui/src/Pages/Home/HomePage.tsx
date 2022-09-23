import React, { useContext } from 'react'


import { UserContext, UserType } from '../../Components/UserContext';
import SignInDialog from '../../Components/SignInDialog/SignInDialog';
import { Table, TableBody, TableRow } from '@mui/material';

export default function HomePage() {

  const userContext = useContext(UserContext);

  const user: UserType = userContext.user;
  
  return (
    <>
      {(user == null) ? <SignInDialog/> :
        <>
          <p>Name: {user.name}</p>
          <p>Token: {user.loginToken}</p>
        </>
      }
    </>
  );
}