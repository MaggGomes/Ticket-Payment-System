module.exports = {
    "env": {
        "browser": true,
        "jquery" : true,
        "node" : true
    },
    "extends": "eslint:recommended",
    "parserOptions": {
        "ecmaVersion": 3
    },
    "rules": {
        "indent": [
            "error",
            "tab",
            { "SwitchCase": 1}
        ],
        "linebreak-style": [
            "error",
            "windows"
        ],
        "quotes": [
            "error",
            "single"
        ],
        "semi": [
            "error",
            "always"
        ]
    }
};