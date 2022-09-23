import { Card } from '@mui/material';
import Grid from '@mui/material/Grid';
import React from 'react';
import Recipient from '../../Interfaces/Recipient';

//import { useState } from 'react';

// create a new interface for prop types
interface RecipientsProps {
    recipient: Recipient;
}


const GiftListForm: React.FC<RecipientsProps> = (recipient: RecipientsProps): JSX.Element => {

    //const [persons, setPersons] = useState([]);

    //etPersons(props);

    return (
        <Card>
            <Grid container margin="5px">
                {recipient && recipient.recipient && recipient.recipient.gifts && recipient.recipient.gifts.map(g => {
                    return <Grid item margin="20px" key={g.id} xs={12} sm={6} md={3}>{g.title}</Grid>
                })}
            </Grid>
        </Card>
    );

}

export default GiftListForm;
