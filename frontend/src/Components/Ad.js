import React, { Component } from 'react';
import Typography from '@material-ui/core/Typography'
import TextField from '@material-ui/core/TextField'
import FormControl from '@material-ui/core/FormControl'
import InputLabel from '@material-ui/core/InputLabel'
import MenuItem from '@material-ui/core/MenuItem'
import Select from '@material-ui/core/Select'
import Fab from '@material-ui/core/Fab'
import Input from '@material-ui/core/Input'
import NavigationIcon from '@material-ui/icons/Navigation'
import axios from 'axios';

class Ad extends Component{
    state = {
        title: "",
        description: "",
        price: "",
        user: {id: 1},
        categories: []
    };

    handleInput = type => event => {
        this.setState({
            [type]: event.target.value
        })
    };
    createAd = () => {
        let adsCat = [...this.state.categories].map(cat => {
            return {id: cat}
        });
        axios.post("/api/create-ad", {
            user: {id: 1},
            title: this.state.title,
            description: this.state.description,
            price: this.state.price,
            categories: adsCat
        })
            .catch(error => console.log(error))
    };

    render(){
        return (
            <div className="ad-Ad cont">
                <div className="ad-header">
                    <Typography component="h4" variant="h4" gutterBottom className={"create-ad-header"}>
                        Create a sweet ad!
                    </Typography>
                </div>
                <div className="title-cont">
                    <TextField
                        id="standard-name"
                        label="Title"
                        value={this.state.title}
                        onChange={this.handleInput('title')}
                        margin="normal"
                    />
                </div>
                <div className="price-cont">
                    <TextField
                        id="standard-name"
                        label="Price"
                        value={this.state.price}
                        onChange={this.handleInput('price')}
                        margin="normal"
                    />
                </div>
                <div className="categories-cont">
                    <FormControl>
                        <InputLabel htmlFor="select-multiple">Name</InputLabel>
                        <Select
                            multiple
                            value={this.state.categories}
                            onChange={this.handleInput('categories')}
                            input={<Input id="select-multiple" />}
                        >
                            {['Furniture', 'Electronics'].map((name, index) => (
                                <MenuItem key={name} value={index + 1}>
                                    {name}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                </div>
                <div className="description-cont">
                    <TextField
                        id="standard-textarea"
                        label="Description"
                        placeholder="Why should I buy this?"
                        multiline
                        value={this.state.description}
                        onChange={this.handleInput('description')}
                        margin="normal"
                    />
                </div>
                <div className="post-cont">
                    <Fab variant="extended" aria-label="Delete" className={"send-button"} onClick={this.createAd}>
                        <NavigationIcon/>
                        Sell Stuff!
                    </Fab>
                </div>
            </div>
        )
    }
}
export default Ad;