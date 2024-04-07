<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Company Report</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Company Report</h1>

    <h2>Employees</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <#list persons as person>
                <tr>
                    <td>${person.id}</td>
                    <td>${person.name}</td>
                </tr>
            </#list>
        </tbody>
    </table>

 <h2>Documents</h2>
    <table>
        <thead>
            <tr>
                <th>Document Name</th>
            </tr>
        </thead>
        <tbody>
            <#list documents as document>
                <tr>
                    <td>${document.name}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>
