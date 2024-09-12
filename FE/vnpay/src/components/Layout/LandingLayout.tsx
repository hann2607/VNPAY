import {Layout} from 'antd';
import {Content} from 'antd/es/layout/layout';
import React from 'react';
import {Outlet} from 'react-router-dom';

interface ILandingLayoutProps {}

const LandingLayout: React.FunctionComponent<ILandingLayoutProps> = () => {
  return (
    <Layout>
      <Content className='content-layout' id='content-layout'>
        <Outlet />
      </Content>
    </Layout>
  );
};

export default LandingLayout;
