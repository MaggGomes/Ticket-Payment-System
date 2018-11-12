'use strict';
module.exports = (sequelize, DataTypes) => {
    var User = sequelize.define('User', {
        name: {
            type: DataTypes.STRING
        },
        age: {
            type: DataTypes.INTEGER
        }
    }, {});
    return User;
};
