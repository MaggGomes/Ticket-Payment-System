module.exports = {
    "env": {
        "browser": true,
        "jquery" : true,
        "node" : true
    },
    "extends": [],
    "parserOptions": {
        "sourceType": "module"
    },
    "rules": {
        "indent": [
            "error",
            "tab",
            { "SwitchCase": 1}
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