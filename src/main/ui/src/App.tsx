import './App.css';
import React, { useContext} from 'react'

import HomePage from './Pages/Home/HomePage';
import SignInPage from './Pages/SignIn/SignInPage';

import {
  BrowserRouter as Router,
  Route,
  Routes
} from "react-router-dom";

import { UserContext, UserContextProvider} from './Components/UserContext';

const  App = (props) => {

  const userContext = useContext(UserContext);

  return (

      // <UserContext.Provider value="">{(props.value) ? <HomePage /> : <SignInPage/>}</UserContext.Provider>
  
      <div>
        <UserContextProvider>
          <Router>
            <Routes>
              <Route path='/' element={<HomePage/>}></Route>
            </Routes>
          </Router>
        </UserContextProvider>
      </div>
  )
};

export default App;
