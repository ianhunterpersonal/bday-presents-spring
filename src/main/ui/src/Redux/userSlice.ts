import { createSlice, PayloadAction } from '@reduxjs/toolkit'

export interface UserState {
    token: string | null;
}

const initialState: UserState = {
    token: null,
}

export const userSlice = createSlice({
    name: 'token',
    initialState,
    reducers: {
        setToken: (state, action: PayloadAction<string>) => {
            state.token = action.payload;
        }
    },
})

// Action creators are generated for each case reducer function
export const { setToken } = userSlice.actions

export default userSlice.reducer
