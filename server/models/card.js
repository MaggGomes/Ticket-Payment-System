'use strict';
module.exports = (sequelize, DataTypes) => {
    const Card = sequelize.define('Card', {
        type: {
            type: DataTypes.INTEGER,
            allowNull: false
        },
        number: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true
        },
        date: {
            type: DataTypes.DATE,
            allowNull: false
        },
        userId: {
            type: DataTypes.INTEGER,
            allowNull: false
        }
    }, {});
    Card.associate = function(models) {
        Card.belongsTo(models.User, {
            foreignKey: 'userId'
        });
    };
    return Card;
};
