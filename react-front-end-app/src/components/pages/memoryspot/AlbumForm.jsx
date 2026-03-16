import {useState} from 'react';
import Button from "../../common/Button";
import InputErrorMessage from "../../forms/input/InputErrorMessage";
import "../pages.css";
import "./album-form.css";
import "./memoryspot.css"

const AlbumForm = ({ album, onClose, onSave, error }) => {

    const initialAlbumData = {
        name: "",
        files: []
    };
    const errorMessage = {
        nameRequired: "Name is required",
        pictureRequired: "Picture is required"
    }

    // if album.name is not available, then consider it as Add album
    const formValues = album.id ? album : initialAlbumData;
    let [form, setForm] = useState(formValues);
    let [hasErrors, setHasError] = useState(false);

    const isValid = () => {
        return form.name && form.files && form.files.length > 0;
    }

    const handleChange = (event) => {
        const element = event.target;
        const elementName = element.name;
        const elementValue = element.value;
        let copyForm = { ...form };
        if(elementName == "files") {
            copyForm[elementName] = element.files;
        } else {
            copyForm[elementName] = elementValue;
        }
        setForm(copyForm);
    };

    const handleClick = (event) => {
        event.preventDefault();
        if (!isValid()) {
            setHasError(true);
        } else {
            setHasError(false);
            const addOrUpdatedAlbum = { ...form };
            //reset the form values for next add or edit
            setForm(initialAlbumData);
            onSave(addOrUpdatedAlbum); // call onSave event handler
        }

    }

    return (

        <div className="mask-layer">
            <div className="popup-container">
                <h4>{album.name ? "Edit" : "Create"} Album</h4>
                {error && <div className="error-msg">{error}</div>}
                <form>

                    <div className="form-group" style={{ paddingBottom: "15px" }}>
                        <input type="text" name="name" value={form.name}
                         onChange={handleChange} placeholder="Album Name" />
                       <InputErrorMessage hasError={hasErrors && form.name === ''}
                       message={errorMessage.nameRequired} />
                    </div>
                    <div className="form-group">
                        <input type="file" name="files" multiple onChange={handleChange} accept=".jpg, .jpeg, .png"/>
                        <InputErrorMessage hasError={hasErrors && form.files.length === 0}
                            message={errorMessage.pictureRequired} />
                    </div>
                    <div className="button-group">
                        <Button onClick={handleClick} label={album.name ? "Update" : "Create"}></Button>&nbsp;&nbsp;
                        <Button onClick={onClose} label="Cancel"></Button>
                    </div>
                </form>
            </div>
        </div>

    )
}

export default AlbumForm;