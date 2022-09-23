import React, { createContext, useState} from 'react';


type GiftType = {
    id: string;
    title: string;
    description: string | null;
    url: string | null;
    multiplicity: string;
}


export type UserType = {
    id: string;
    loginToken: string
    name: string | null
    password: string
    gifts: GiftType[];
}

type UserContextType = {
    user: UserType | null;
    setUser: (user: UserType) => void;
}

const userContextDefaultValues: UserContextType = {
    user: null,
    setUser: (user: UserType) => {}
}
export const UserContext = createContext<UserContextType>(userContextDefaultValues);

export function UserContextProvider(props: any) {

     const [user, setUser] = useState<UserType | null>(userContextDefaultValues.user)

    return (
        <UserContext.Provider value={{user, setUser}}>
            {props.children}
        </UserContext.Provider>  
    );

}




