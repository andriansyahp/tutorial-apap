import React, { Component } from 'react';
import classes from './Restorans.module.css';
import Restoran from '../../components/Restoran/Restoran';
import axios from '../../axios-restoran';
import Modal from '../../components/UI/Modal/Modal';
import Button from '../../components/UI/Button/Button';


class Restorans extends Component{
    constructor(props){
        super(props);
        this.state = {
            restorans: [],
            isCreate: false,
            isEdit: false,
            isLoading: true,
            nama: "",
            alamat: "",
            nomorTelepon: "",
            rating: "",
            searchedValue: ""
        }
    }

    componentDidMount(){
        this.loadRestorans();
    }

    addRestoranHandler = () => {
        this.setState({ isCreate: true });
        this.resetRestoranState();
    }

    canceledHandler = () => {
        this.setState({ isCreate: false, isEdit: false });
    }

    loadRestorans = async () => {
        const fetchedRestorans = [];
        const response = await axios.get("/restorans");
        for (let key in response.data){
            fetchedRestorans.push({
                ...response.data[key]
            });
        }
        this.setState({
            restorans: fetchedRestorans
        });
    }

    shouldComponentUpdate(nextProps, nextState){
        console.log("shouldComponentUpdate()");
        return true;
    }

    loadingHandler = () => {
        const currentLoading = this.state.isLoading;
        this.setState( {isLoading: !(currentLoading)} );
        console.log(this.state.isLoading);
    }

    renderForm() {
        const { isEdit } = this.state;
        return(
            <form>
                <input
                    className={classes.Input}
                    name="nama"
                    type="text"
                    placeholder="Nama"
                    value={this.state.nama}
                    onChange={this.changeHandler}
                />
                <input
                    className={classes.Input}
                    name="nomorTelepon"
                    type="number"
                    placeholder="Nomor Telepon"
                    value={this.state.nomorTelepon}
                    onChange={this.changeHandler}
                />
                <input
                    className={classes.Input}
                    name="alamat"
                    type="text"
                    placeholder="Alamat"
                    value={this.state.alamat}
                    onChange={this.changeHandler}
                />
                <input
                    className={classes.Input}
                    name="rating"
                    type="number"
                    placeholder="Rating"
                    value={this.state.rating}
                    onChange={this.changeHandler}
                />
                <Button btnType="Danger" onClick={this.canceledHandler}>
                    CANCEL
                </Button>
                <Button btnType="Success" onClick={
                    isEdit ? this.submitEditRestoranHandler : this.submitAddRestoranHandler
                    }
                >
                    SUBMIT
                </Button>
            </form>
        );
    }

    resetRestoranState() {
        this.setState({
            nama: "",
            alamat: "",
            nomorTelepon: "",
            rating: ""
        })
    }

    changeHandler = event => {
        console.log("changeHandler()");
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    onSearch = (event) => {
        console.log("onSearch()");
        this.setState({ searchedValue: event.target.value });
    }

    submitAddRestoranHandler = event => {
        event.preventDefault();
        this.setState({ isLoading: true });
        this.addRestoran();
        this.canceledHandler();
    }

    submitEditRestoranHandler = event => {
        console.log("editing");
        event.preventDefault();
        this.setState({ isLoading: true });
        this.editRestoran();
        this.canceledHandler();
    }

    async addRestoran() {
        const restoranToAdd = {
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await axios.post("/restoran", restoranToAdd);
        await this.loadRestorans();
    }

    async editRestoran() {
        const restoranToEdit = {
            idRestoran: this.state.idRestoran,
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await axios.put("/restoran/" + this.state.idRestoran, restoranToEdit);
        await this.loadRestorans();
        this.canceledHandler();
    }

    async deleteRestoranHandler(restoranId){
        await axios.delete(`/restoran/${restoranId}`);
        await this.loadRestorans();
    }

    editRestoranHandler(restoran){
        this.setState({
            isEdit: true,
            idRestoran: restoran.idRestoran,
            nama: restoran.nama,
            nomorTelepon: restoran.nomorTelepon,
            rating: restoran.rating,
            alamat: restoran.alamat
        })
    }

    render(){
        console.log("render()");
        const filteredItems = this.state.restorans.filter((restoran) => restoran.nama.toLowerCase().includes(this.state.searchedValue.toLowerCase()));
        return(
            <React.Fragment>
                <Modal 
                    show={this.state.isCreate || this.state.isEdit}
                    modalClosed={this.canceledHandler}
                >
                    {this.renderForm()}
                </Modal>
                <div className={classes.Title}> All Restorans </div>
                <div className={classes.ButtonLayout}>
                    <button 
                        className={classes.AddRestoranButton}
                        onClick={this.addRestoranHandler}
                    >
                        + Add New Restoran 
                    </button>
                </div>
                <br></br>
                <div>
                    <input 
                        style={{
                            width: '95%',
                            height: '30px',
                            marginLeft: '2%',
                            textAlign: 'center'
                        }} 
                        type="text" 
                        placeholder="Search restoran..." onChange={this.onSearch} 
                        value={this.state.searchedValue} />
                </div>
                <div className={classes.Restorans}>
                    {this.state.restorans &&
                        filteredItems.map(restoran =>
                            <Restoran 
                                key={restoran.id}
                                nama={restoran.nama}
                                alamat={restoran.alamat}
                                nomorTelepon={restoran.nomorTelepon}
                                edit={()=>this.editRestoranHandler(restoran)}
                                delete={()=>this.deleteRestoranHandler(restoran.idRestoran)}
                            />
                        )
                    }
                </div>
            </React.Fragment>
        );
    }
}

export default Restorans;