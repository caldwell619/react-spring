# Sending the request from React
This guide is to explain how requests are sent to Spring from React

_<Caveat:>_ Normally, we'd want user authentication to be able to do this. We don't just want anyone sending requests.

If this does'nt suck, put in an issue or let me know somehow that that's worth explaining 

## Setting up the forms
We're going to have a super simple form to send to our `API`


React handles for input with `state` and the `onChange()` method.

We need a new directory inside of `frontend/src/` called `Components/`
- Inside of `Components/`, create a file named `Ad.js`. Notice the capital letter because this file will be a JavaScript class
- We need to be able to send the following information: `title`, `description`, `price`, `user`, `categories`, which will allbe handled by `state`
- There's a very simple method for handling input, but it is tricky upon 1st glance
```$xslt
handleInput = type => event => {
    this.setState({ 
        [type]: event.target.value
    })
}
```
Looks like a normal function, but wait. Plot twist. It's a [higher order function](https://blog.bitsrc.io/understanding-higher-order-functions-in-javascript-75461803bad).
Our function, `handleInput` accepts a function with an argument, `type`, which then returns a function that takes an argument `event`. Uhh. Okay..
<br>

So this is important, and is broken down [here](). For now, let's move on.

Add a `state` property just below the method signature with properties:
```$xslt
state = {
        title: "",
        description: "",
        price: "",
        user: {id: 1},
        categories: []
    };
```
We're hardcoding in the user value for now. Kinda seems off that we need to make it an object, but remember our mystical friend, Spring, is looking for a `User` class as part of an `Ad` entity.


### Don't reinvent the wheel
I know what you're thinking. Bootstrap sucks! I can make designs way better than that. Yes, you can. Great. However, `CSS` frameworks help you move quickly through prototyping, and even in production.

Don't be lame for no reason. We're going to use [Material-UI](https://material-ui.com/). It's fast to implement, looks great, and is 97% functional with copy / paste.
- From the `frontend/` directory, run `npm i -S @material-ui/core @material-ui/icons` to install and save both dependencies to `package.json`
- Add the `Roboto` font and the icons to the `index.html` file in the `public/` directory
    - `<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">`
    - `<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">`
    
### Adding Material UI to our components
In my opinion, `Material-UI` does'nt have great documentation. You have to go digging for a lot of the smaller pieces. We will touch on common ones, but messa round and find what you like

Back in `Ad.js`,
- If you haven't already, add the `handleInput` method to the class `Ad`
- At the top of the page, import the `Material-UI` components we need. The pattern is, `import ComponentName from '@material-ui/core/ComponentName`
We will need :
```$xslt
import Typography from '@material-ui/core/Typography'
import TextField from '@material-ui/core/TextField'
import FormControl from '@material-ui/core/FormControl'
import InputLabel from '@material-ui/core/InputLabel'
import MenuItem from '@material-ui/core/MenuItem'
import Select from '@material-ui/core/Select'
import Fab from '@material-ui/core/Fab'
import Input from '@material-ui/core/Input'
import NavigationIcon from '@material-ui/icons/Navigation'
```
At this point, you can choose to build out your own layout, or just use [this one]()