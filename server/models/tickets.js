'use strict';
module.exports = (sequelize, DataTypes) => {
    const Ticket = sequelize.define('Ticket', {
        seatNumber: {
            type: DataTypes.INTEGER,
            allowNull: false
        },
        showId: {
            type: DataTypes.INTEGER,
            allowNull: false
        },
        userId: {
            type: DataTypes.INTEGER,
            allowNull: false
        }
    }, {});
    Ticket.associate = function(models) {
        Ticket.belongsTo(models.Show, {
            foreignKey: 'showId'
        });
        Ticket.belongsTo(models.User, {
            foreignKey: 'userId'
        });
    };
        return Ticket;
};
