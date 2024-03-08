const axios = require('axios');

exports.handler = async (event) => {
  const apiEndpoint = 'https://3121maymp2.execute-api.us-east-2.amazonaws.com/Prod/heartbeat';

  try {
    const response = await axios.get(apiEndpoint);
    console.log('API Response:', response.data);
    return response.data;
  } catch (error) {
    console.error('Error calling API:', error);
    throw error;
  }
};
