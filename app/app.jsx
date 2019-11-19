import React, { Component } from "react";
import ReactDOM from "react-dom";
import { Widget, addResponseMessage, addLinkSnippet } from "react-chat-widget";
import PropTypes from "prop-types";

import "react-chat-widget/lib/styles.css";

import "./App.css";

class Chatbotroom extends Component {
    constructor(props) {
        super(props);
        this.state = { chatbotprovider: this.props.provider, chatboturl: this.props.url, chatsession:this.props.session };
        this.handleNewUserMessage = this.handleNewUserMessage.bind(this);
    }

    componentDidMount() {
        addResponseMessage(`Welcome to this awesome chat powered by ${this.props.provider}!`);
    }

    async getDataFetch(newMessage) {
        const headers = new Headers();
        
        let chatsession = "".concat(`${this.props.session}`);
        let fullchatsession = "JSESSIONID=".concat(`${this.props.session}`);
        
        headers.append("Content-Type", "application/json");
        headers.append("cookie", fullchatsession);
        headers.append("content-length", "300");

		
        const body = `{"sender":"${chatsession}","message":"${newMessage}"}`;
	
        const init = {
            method: "POST",
            headers,
            body
        };

        // Default value of `${this.props.url}` http://localhost:5005/webhooks/rest/webhook

        let chatbotCurrentUrl = "".concat(`${this.props.url}`);
        fetch(chatbotCurrentUrl, init)
            .then(response => {
                return response.json(); // or .json() or .blob() ...
            })
            .then(data => {
                data.forEach(element => {
                    if (typeof element.text !== "undefined") {
                        addResponseMessage(String(element.text));
                    } else if (typeof element.image !== "undefined") {
                        //addResponseMessage(`<img src= '${element.image}' width="400"  height="235" alt="picture">`);
                        let imageLink = {
                            title: "My awesome Picture",
                            link: String(element.image),
                            target: "_blank"
                        };
                        addLinkSnippet(imageLink);
                        //addResponseMessage(String(element.image));
                    }
                });
            })
            .catch(e => {
                console.log(`Error retrieving chatbot answer`);
            });
    }

    handleNewUserMessage(newMessage) {
        console.log(`New message incoming! ${newMessage}`);
        this.getDataFetch(newMessage);
    }

    render() {
        return (
            <div className="App">
                <Widget handleNewUserMessage={this.handleNewUserMessage} />
            </div>
        );
    }
}

Chatbotroom.propTypes = {
    provider: PropTypes.string,
    url: PropTypes.string,
    session:PropTypes.string
};


const Application = () => {
    const chatbot_provider = document.getElementById("chatbotProvider").innerText;
    const chatbot_url = document.getElementById("chatbotUrl").innerText;
    const chatbot_session = document.getElementById("chatsession").innerText;
    
    return <Chatbotroom provider={chatbot_provider} url={chatbot_url} session={chatbot_session} />;
};

ReactDOM.render(<Application />, document.getElementById("app"));
