'use strict';
module.exports = (sequelize, DataTypes) => {
	const Transaction = sequelize.define('Transaction', {
		productId: {
			type: DataTypes.INTEGER,
			allowNull: true
		},
		ticketId: {
			type: DataTypes.STRING,
			allowNull: true
		},
		userId : {
		    type: DataTypes.STRING,
			allowNull: false
		},
		price: {
			type: DataTypes.DOUBLE,
			allowNull: false
		}
	}, {});
	Transaction.associate = function(models) {
		Transaction.belongsTo(models.Product, {
			foreignKey: 'productId'
		});
		Transaction.belongsTo(models.Ticket, {
			foreignKey: 'ticketId'
		});
		Transaction.belongsTo(models.User, {
			foreignKey: 'userId'
		});
	};
	return Transaction;
};
