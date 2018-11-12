'use strict';
module.exports = (sequelize, DataTypes) => {
    const User = sequelize.define('User', {
        name: {
            type: DataTypes.STRING,
            allowNull: false
        },
        username: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        nif: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true
        },
        keyN: {
            type: DataTypes.SRING,
            allowNull: false,
        },
        keyE: {
            type: DataTypes.SRING,
            allowNull: false,
        }
    },
        {
            indexes:[
                {
                    unique:true,
                    fields:['keyN', 'keyE']
                }
            ]
        });
    return User;
};
