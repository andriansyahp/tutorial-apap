import React from "react";
import List from "./components/List";
import EmptyState from "./components/EmptyState";
import dummyItems from "./items.json";
export default class App extends React.Component {
  // for class based component, you need to provide render function

  constructor(props){
    super(props);
    this.state={
      favItems: [],
      favToggle: false
    };
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange() {
    this.setState({
      favToggle: !this.state.favToggle
    })
  }

  handleItemClickMenu = item => {
    const newItems = [...this.state.favItems];
    const newItem = {...item};

    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    if(targetInd<0) newItems.push(newItem);
    else if (targetInd>=0 && newItem.isFav)newItems.splice(targetInd, 1);
    
    this.setState({favItems: newItems});
  }

  handleItemClickFavorite = item => {
    const newItems = [...this.state.favItems];
    const newItem = {...item};

    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    if(targetInd<0) newItems.push(newItem);
    else newItems.splice(targetInd, 1);
    
    this.setState({favItems: newItems});
  }

  render() {
    const { favItems } = this.state;

    return (
      <div className="container-fluid">
        <h1 className="text-center">
          Welcome!
          <small>Class-based</small>
        </h1>
        <div className="container pt-3">
          <div className="float-right">
            <label>Show Favorites</label>
            <input
              type="checkbox"
              checked={this.state.favToggle}
              onChange={this.handleChange} />
          </div>
          <div className="row">
            <div className="col-sm">
              <List
                title="Our Menu"
                isFav = {false}
                items={dummyItems}
                onItemClick={this.handleItemClickMenu}
              />
            </div>
            {(!this.state.favToggle)
              ?
              null
              :
              (this.state.favItems.length > 0)
                ?
                <div className="col-sm">
                  <List
                    title="My Favorite"
                    isFav={true}
                    items={favItems}
                    onItemClick={this.handleItemClickFavorite} />
                </div>
                :
                <div className="col-sm">
                  <EmptyState
                    title="My Favorite"
                  />
                </div>}
          </div>
        </div>
      </div>
    );
  }
}