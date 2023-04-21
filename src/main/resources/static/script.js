document.getElementById('submit').addEventListener('click', async function () {
    const command = document.getElementById('command').value.trim();
    const output = document.getElementById('output');

    if (command) {
        const [action, ...args] = command.split(' ');
        try {
            const response = await fetch(`/directory/${action.toLowerCase()}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ args })
            });

            if (response.ok) {
                const result = await response.text();
                output.textContent += result + '\n';
            } else {
                output.textContent += 'Error: ' + response.statusText + '\n';
            }
        } catch (error) {
            output.textContent += 'Error: ' + error.message + '\n';
        }

        document.getElementById('command').value = '';
    }
});






