'use strict';
module.exports = (sequelize, DataTypes) => {
	const TicketTransaction = sequelize.define('TicketTransaction', {
		userId : {
			type: DataTypes.STRING,
			allowNull: false
		},
		showId : {
			type: DataTypes.INTEGER,
			allowNull: false
		},
		showName : {
			type: DataTypes.STRING,
			allowNull: false
		},
		showDescription: {
			type: DataTypes.STRING,
			allowNull: false
		},
		noTickets : {
			type: DataTypes.INTEGER,
			allowNull: false
		},
		date : {
			type: DataTypes.DATE,
			allowNull: false
		},
		price: {
			type: DataTypes.DOUBLE,
			allowNull: false
		},
		totalPrice: {
			type: DataTypes.DOUBLE,
			allowNull: false
		}
	}, {});
	TicketTransaction.associate = function(models) {
		TicketTransaction.belongsTo(models.Show, {
			foreignKey: 'showId'
		});
		TicketTransaction.belongsTo(models.User, {
			foreignKey: 'userId'
		});
	};
	return TicketTransaction;
};
