import { useState } from "react";
import Button from "../../common/Button";
import InputErrorMessage from "../../forms/input/InputErrorMessage";

const ReminderForm = ({ reminder, onClose, onSave }) => {

    const initialReminderData = {
        id: null,
        name: "",
        date: "",
        time: "",
        notes: ""
    };
    const errorMessage = {
        nameRequired: "Name is required",
        dateRequired: "Date is required",
        notesRequired: "Note is required"
    }

    // if reminder.name is not available, then consider it as Add reminder
    const formValues = reminder.id ? reminder : initialReminderData;
    let [form, setForm] = useState(formValues);
    let [hasErrors, setHasError] = useState(false);

    const isValid = () => {
        return form.name && form.date && form.notes
    }

    const handleChange = (event) => {
        const element = event.target;
        const elementName = element.name;
        const elementValue = element.value;
        let copyForm = { ...form };
        copyForm[elementName] = elementValue;
        setForm(copyForm);
    };

    const getDateForInput = (date) => {
        return date ? date.replaceAll("/", "-") : ""; // <input type="date" accepts with -
    }

    const handleClick = (event) => {
        event.preventDefault();
        const addOrUpdatedReminder = { ...form };
        if (!isValid()) {
            setHasError(true);
        } else {
            setHasError(false);
            //reset the form values for next add or edit
            setForm(initialReminderData);
            onSave(addOrUpdatedReminder); // call onSave event handler
        }

    }

    return (

        <div className="mask-layer">
            <div className="popup-container">
                <h4>{reminder.name ? "Edit" : "Add"} Reminder</h4>

                <input type="text" name="name" placeholder="Name"
                    onChange={handleChange} value={form.name || ""} />
                <InputErrorMessage hasError={hasErrors && form.name === ''}
                    message={errorMessage['nameRequired']} />

                <input type="date" name="date" placeholder="Date"
                    onChange={handleChange} value={getDateForInput(form.date || "")} />
                    <InputErrorMessage hasError={hasErrors && form.date === ''}
                    message={errorMessage['dateRequired']} />

                <input type="time" name="time" placeholder="Time"
                    onChange={handleChange} value={form.time || ""} />

                <textarea name="notes" rows={5} placeholder="Notes"
                    onChange={handleChange} value={form.notes || ""} ></textarea>
                    <InputErrorMessage hasError={hasErrors && form.notes === ''}
                    message={errorMessage['notesRequired']} />

                <Button type="submit" onClick={handleClick} label={"Save"}></Button>
                <Button onClick={onClose} label={"Cancel"}></Button>
            </div>
        </div>
    )
}
export default ReminderForm;
