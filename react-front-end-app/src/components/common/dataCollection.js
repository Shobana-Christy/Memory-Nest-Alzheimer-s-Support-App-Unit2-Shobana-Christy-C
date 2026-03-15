
export const baseUrl = "http://localhost:8080";

export async function logout() {

}

export async function getLoggedInUser() {
    try {
        let response = await fetch(baseUrl+"/user", { credentials: "include" });
        if (response.ok) {
            return response.json();
        }
    } catch (error) {
        return null;
    }
    return null;
}

// async ensures caller of this function will not wait for the response
export async function fetchReminders() {
    try {
        let time = new Date().getTime();
        // await ensures the request will wait for the response
        let response = await fetch(baseUrl+"/reminders?time="+time, { credentials: "include" });
        if (response.ok) {
            return response.json();
        }
    } catch (error) {
        return [];
    }
    return [];
}

export async function addReminder(reminder) {
    try {
        let response = await fetch(baseUrl+"/reminders", {
            method: "POST",
            credentials: "include",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(reminder)
        });

        if (response.ok) {
            return response.json();
        }
    } catch (error) {
        return null;
    }

    return null;
}

export async function updateReminder(reminder) {
    try {
        let response = await fetch(baseUrl+"/reminders/" + reminder.id, {
            method: "PUT",
            credentials: "include",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(reminder)
        });

        if (response.ok) {
            return response.json();
        }
    } catch (error) {
        return null;
    }

    return null;
}

export async function deleteReminder(reminderId) {
    try {
        let response = await fetch(baseUrl+"/reminders/"+reminderId, {
            method: "DELETE",
            credentials: "include",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            return response.text();
        }
    } catch (error) {
        return null;
    }

    return null;
}

// retrieve the json from public folder
// async ensures caller of this function will not wait for the response
export async function fetchAlbums() {
    try {
        let time = new Date().getTime();
        let response = await fetch(baseUrl+"/memoryspot/albums?time="+time, {
            credentials: "include"
        });
        if (response.ok) {
            return response.json();
        }
    } catch (error) {
        return [];
    }

    return [];
}

export async function fetchAlbumContent(albumName) {
    try {
        let time = new Date().getTime();
        let response = await fetch(baseUrl+"/memoryspot/albums/"+albumName, {
            credentials: "include"
        });
        if (response.ok) {
            return response.json();
        }
    } catch (error) {
        return [];
    }

    return [];
}

export async function createAlbum(albumDetails) {
    try {
        let form = new FormData();
        form.append("name", albumDetails.name);
        for(let i=0; i<albumDetails.files.length; i++) {
            form.append("pictures", albumDetails.files[i]);
        }
        let time = new Date().getTime();
        let response = await fetch(baseUrl+"/memoryspot/albums", {
            method: "POST",
            credentials: "include",
            body: form
        });
        if (response.ok) {
            return response.text();
        }
    } catch (error) {
        return null;
    }

    return null;
}
