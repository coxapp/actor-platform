import _ from 'lodash';
import Immutable from 'immutable';
import keymirror from 'keymirror';

import React from 'react';

import CreateGroupActionCreators from '../../../actions/CreateGroupActionCreators';

import ContactStore from '../../../stores/ContactStore';

import ContactItem from './ContactItem.react';

const STEPS = keymirror({
  NAME_INPUT: null,
  CONTACTS_SELECTION: null
});

class CreateGroupForm extends React.Component {
  static displayName = 'CreateGroupForm'

  constructor(props) {
    super(props);

    this.state = {
      step: STEPS.NAME_INPUT,
      selectedUserIds: new Immutable.Set()
    };

    this.onNameChange = this.onNameChange.bind(this);
    this.onNameSubmit = this.onNameSubmit.bind(this);

    this.onMembersSubmit = this.onMembersSubmit.bind(this);

    this.onContactToggle = this.onContactToggle.bind(this);
  }

  render() {
    let stepForm;

    switch (this.state.step) {
      case STEPS.NAME_INPUT:
        stepForm = (
          <form onSubmit={this.onNameSubmit}>
            <p>Group name: <input onChange={this.onNameChange} value={this.state.name}/></p>
          </form>
        );
        break;
      case STEPS.CONTACTS_SELECTION:
        let contactList = _.map(ContactStore.getContacts(), (contact, i) => {
          return (
            <ContactItem contact={contact} key={i} onToggle={this.onContactToggle}/>
          );
        });

        stepForm = (
          <div>
            <form onSubmit={this.onMembersSubmit}>
              <input type="submit" value="Create group"/>
              <ul className="contacts__list">
                {contactList}
              </ul>
            </form>
          </div>
        );
        break;
    }

    return (
      <div>
        {stepForm}
      </div>
    );
  }

  onContactToggle(contact, isSelected) {
    if (isSelected) {
      this.setState({selectedUserIds: this.state.selectedUserIds.add(contact.uid)});
    } else {
      this.setState({selectedUserIds: this.state.selectedUserIds.remove(contact.uid)});
    }
  }

  onNameChange(event) {
    event.preventDefault();

    this.setState({name: event.target.value});
  }

  onNameSubmit(event) {
    event.preventDefault();
    this.setState({step: STEPS.CONTACTS_SELECTION});
  }

  onMembersSubmit(event) {
    event.preventDefault();
    CreateGroupActionCreators.createGroup(this.state.name, null, this.state.selectedUserIds.toJS());
  }
}

export default CreateGroupForm;
