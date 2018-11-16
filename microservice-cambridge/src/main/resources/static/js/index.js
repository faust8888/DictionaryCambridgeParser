import React from "react";
import ReactDom from "react-dom";


class App extends React.Component {

    render() {
        return (
        <p>Hello, World! Good morning Russia!</p>
        )
    }
}

ReactDom.render(<App />, document.getElementById('react'));