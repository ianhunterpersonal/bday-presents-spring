
import { IGift } from './Gift';

export interface IRecipient {
    id: string,
    name: string,
    email: string,
    gifts: IGift[]
};


