'use strict';
module.exports = (sequelize, DataTypes) => {
	const Ticket = sequelize.define('Ticket', {
	    id: {
	      type: DataTypes.UUIDV4,
			primaryKey: true,
			allowNull: false
		},
		seatNumber: {
			type: DataTypes.INTEGER,
			allowNull: false
		},
		showName:{
	    	type: DataTypes.STRING,
			allowNull:false
		},
		showDate:{
	    	type: DataTypes.DATE,
			allowNull:false
		},
		used: {
	    	type: DataTypes.BOOLEAN,
			default: false
		},
		showId: {
			type: DataTypes.INTEGER,
			allowNull: false
		},
		userId: {
			type: DataTypes.STRING,
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
